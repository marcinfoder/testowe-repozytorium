package com.capgemini.persistence.domain;

public class Photo {
	private long photoId;
	private long galleryId;
	private String name;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("photoId").append(photoId).
		append("galleryId").append(galleryId).
		append("name").append(name);
		return sb.toString();
	}
	
	public long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}
	public long getGalleryId() {
		return galleryId;
	}
	public void setGalleryId(long galleryId) {
		this.galleryId = galleryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
