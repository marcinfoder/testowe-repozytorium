package com.capgemini.rest.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.DateComparator;
import com.capgemini.NavigationNames;
import com.capgemini.persistence.domain.Campaign;
import com.capgemini.persistence.domain.CampaignStep;
import com.capgemini.persistence.domain.Message;
import com.capgemini.persistence.domain.TwitterAccess;
import com.capgemini.persistence.domain.User;
import com.capgemini.rest.model.MessageForm;
import com.capgemini.service.CampaignService;
import com.capgemini.service.MessageService;
import com.capgemini.service.TwitterAccessService;
import com.capgemini.service.UserService;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

@Controller
public class MessagesController {

	@Autowired
	private Twitter twitter;

	@Autowired
	private TwitterAccessService twitterAccessService;

	@Autowired
	private CampaignService campService;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-hashtag")
	@ResponseBody
	public HashAndSteps getCampaignHashtag(@RequestParam long campaignId) {
		Campaign camp = campService.getCampaignById(campaignId);
		List<CampaignStep> stepsList = (List<CampaignStep>) campService
				.getStepsByCampaignId(campaignId);

		HashAndSteps has = new HashAndSteps();
		has.setHashtag(camp.getHashTag() + " ");
		has.setStepsList(stepsList);
		return has;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/messages")
	public String getMessages(Model model, Principal principal) {

		List<Campaign> campaignList = (List<Campaign>) campService
				.getCampaignByUserLogin(principal.getName());
		model.addAttribute("campaignList", campaignList);
		
		if(campaignList.isEmpty() == false) {
			model.addAttribute("hashtag", campaignList.get(0).getHashTag());
			List<CampaignStep> campaignStepList = (List<CampaignStep>) campService
					.getStepsByCampaignId(campaignList.get(0).getCampaignId());
			model.addAttribute("campaignStepList", campaignStepList);
	
//			List<Message> messageList = (List<Message>) messageService
//					.getMessageByCampaignId(campaignList.get(0).getCampaignId());
//			model.addAttribute("Tweets", messageList);
		}
		MessageForm form = new MessageForm();
		form.setPublishDate(new Date());
		model.addAttribute("MessageForm", form);

		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}

	// do i need it?
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/messages/{campId}")
	public String getMessagesByCampId(@PathVariable long campId, Model model,
			Principal principal) {

		List<Campaign> campaignList = (List<Campaign>) campService
				.getCampaignByUserLogin(principal.getName());
		model.addAttribute("campaignList", campaignList);

		List<CampaignStep> campaignStepList = (List<CampaignStep>) campService
				.getStepsByCampaignId(campId);
		model.addAttribute("campaignStepList", campaignStepList);

//		List<Message> messageList = (List<Message>) messageService
//				.getMessageByCampaignId(campId);
//		model.addAttribute("Tweets", messageList);

		MessageForm form = new MessageForm();
		form.setPublishDate(new Date());
		model.addAttribute("MessageForm", form);

		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-messages/{campId}/{stepId}")
	public String getMessagesByStep(@PathVariable long campId,
			@PathVariable long stepId, Model model, Principal principal) {

		List<Message> messageList = (List<Message>) messageService
				.getMessageByStepId(stepId);
		model.addAttribute("messageList", messageList);

		model.addAttribute("campId", campId);

		model.addAttribute("page", NavigationNames.CAMPAIGN_PREVIEW);
		return "campaign-step-messages";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/messages", params = { "send" })
	public String sendMessage(Model model, @ModelAttribute("MessageForm") @Valid MessageForm messageForm,
			@RequestParam String send, Principal principal, BindingResult bind) {

        List<Campaign> campaignList = (List<Campaign>) campService
                        .getCampaignByUserLogin(principal.getName());
        model.addAttribute("campaignList", campaignList);
        
        if(campaignList.isEmpty() == false) {
        	model.addAttribute("hashtag", campaignList.get(0).getHashTag());
        	List<CampaignStep> campaignStepList = (List<CampaignStep>) campService
                        .getStepsByCampaignId(campaignList.get(0).getCampaignId());
        	model.addAttribute("campaignStepList", campaignStepList);
		}
		
		if(bind.hasErrors()) {
			return "messages";
		}
		
		Message message = prepareMessage(messageForm, principal.getName());

		Campaign campaign = campService.getCampaignById(messageForm.getCampaignId());
		CampaignStep campStep = campService.getStepById(messageForm.getStepId());
		
		if(campaign == null || campStep == null){
			model.addAttribute("isNull", true);
		}
		messageForm.setText(campaign.getHashTag() + " " + messageForm.getText());

		Date date = new Date();
		if (date.after(campStep.getEndDate())) {
			model.addAttribute("isFuture", true);
		}
		else if (date.before(campStep.getStartDate())) {
			model.addAttribute("isPast", true);
		}
		else {
			try {
				TwitterAccess ta = twitterAccessService.findByLogin(principal.getName());
                twitter.setOAuthAccessToken(new AccessToken(ta.getAccessToken(), ta.getAccessTokenSecret()));
				Status status = twitter.updateStatus(messageForm.getText());
				
				message.setTweetId(status.getId());
                message.setTwitterPublishAt(date);
                message.setTwitterPublished(true);
                message.setFacebookPublishAt(date);
                message.setFacebookPublished(true);
                
                messageService.addMessage(message);
                model.addAttribute("submited", true);
                
			} catch (TwitterException e) {
				model.addAttribute("isTwitterError", true);
				return "messages";
			}
		}
		
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/messages", params = { "add" })
	public String addMessage(@ModelAttribute("MessageForm") @Valid MessageForm form,
			@RequestParam String add, Model model, Principal principal, BindingResult bind) {
		
		List<Campaign> campaignList = (List<Campaign>) campService
				.getCampaignByUserLogin(principal.getName());
		model.addAttribute("campaignList", campaignList);
		
		if(campaignList.isEmpty() == false) {
			model.addAttribute("hashtag", campaignList.get(0).getHashTag());
			List<CampaignStep> campaignStepList = (List<CampaignStep>) campService
					.getStepsByCampaignId(campaignList.get(0).getCampaignId());
			model.addAttribute("campaignStepList", campaignStepList);
		}

		if(bind.hasErrors()) {
			return "messages";
		}
		
		Message message = prepareMessage(form, principal.getName());
		
		Campaign campaign = campService.getCampaignById(form.getCampaignId());
		CampaignStep campStep = campService.getStepById(form.getStepId());
		if(campaign == null || campStep == null) {
			model.addAttribute("isNull", true);
			return "messages";
		}
		form.setText(campaign.getHashTag() + " " + form.getText());

		Date date = new Date();
		if (form.getPublishDate().before(date) || form.getPublishDate().before(campStep.getStartDate())) {
			model.addAttribute("isPast", true);
		}
		else if (form.getPublishDate().after(campStep.getEndDate())) {
			model.addAttribute("isFuture", true);
		}
		else {
			message.setTwitterPublishAt(form.getPublishDate());
			message.setTwitterPublished(false);
			message.setFacebookPublishAt(form.getPublishDate());
			message.setFacebookPublished(false);
			
			messageService.addMessage(message);
			model.addAttribute("submited", true);
		}
		
		model.addAttribute("MessageForm", form);	
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}

	private Message prepareMessage(MessageForm form, String username){
		Message message = new Message();
		User user = userService.getUserByLogin(username);

		message.setGroupId(user.getGroupId());
		message.setUserId(user.getId());
		message.setCampaignId(form.getCampaignId());
		message.setStepId(form.getStepId());
		message.setCreatedAt(new Date());
		message.setTwitterContent(form.getText());
		message.setFacebookContent(form.getText());			
		
		return message;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-step-message-delete")
	public String removeMessage(@RequestParam("messageId") long messageId,
			@RequestParam("stepId") long stepId,
			@RequestParam("campId") long campId, Model model) {

		if (!messageService.getMessageById(messageId).isAlreadySent()) {
			messageService.deleteMessageById(messageId);
		}

		return "redirect:/service/campaign-step-messages/" + campId + "/"
				+ stepId;
	}

	private class HashAndSteps {
		private List<CampaignStep> stepsList;
		private String hashtag;

		public List<CampaignStep> getStepsList() {
			return stepsList;
		}

		public void setStepsList(List<CampaignStep> stepsList) {
			this.stepsList = stepsList;
		}

		public String getHashtag() {
			return hashtag;
		}

		public void setHashtag(String hashtag) {
			this.hashtag = hashtag;
		}
	}
}
