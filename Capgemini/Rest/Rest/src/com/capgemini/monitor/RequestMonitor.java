package com.capgemini.monitor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.codegoogle.tcpmon.MainWindow;

public class RequestMonitor {

	private String serverPort;
	private String serverName;
	private String localPort;
	private Boolean ssl;

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setLocalPort(String localPort) {
		this.localPort = localPort;
	}

	public void setSsl(Boolean ssl) {
		this.ssl = ssl;
	}

	public void start() {
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				MainWindow window = new MainWindow(new String[] {});
				window.setVisible(true);
				try {
					setLocalPort(window);

					setServerName(window);

					setServerPort(window);

					setSSL(window);

					addMonitor(window);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}

			private void addMonitor(MainWindow window)
					throws NoSuchFieldException, IllegalAccessException {
				Field button = MainWindow.class.getDeclaredField("bAddMonitor");
				button.setAccessible(true);
				((JButton)button.get(window)).getActionListeners()[0].actionPerformed(new ActionEvent("", 0, ""));
			}

			private void setSSL(MainWindow window) throws NoSuchFieldException,
					IllegalAccessException {
				Field sslField = MainWindow.class.getDeclaredField("cbSsl");
				sslField.setAccessible(true);
				((JCheckBox)sslField.get(window)).setSelected(ssl);
			}

			private void setServerPort(MainWindow window)
					throws NoSuchFieldException, IllegalAccessException {
				Field serverPortField = MainWindow.class
						.getDeclaredField("tfRemotePort");
				serverPortField.setAccessible(true);
				((JTextField)serverPortField.get(window)).setText(serverPort);
			}

			private void setServerName(MainWindow window)
					throws NoSuchFieldException, IllegalAccessException {
				Field serverNameField = MainWindow.class
						.getDeclaredField("tfRemoteHost");
				serverNameField.setAccessible(true);
				((JTextField)serverNameField.get(window)).setText(serverName);
			}

			private void setLocalPort(MainWindow window)
					throws NoSuchFieldException, IllegalAccessException {
				Field localPortField = MainWindow.class
						.getDeclaredField("tfLocalPort");
				localPortField.setAccessible(true);
				((JTextField)localPortField.get(window)).setText(localPort);
			}
		};
		EventQueue.invokeLater(thread);
	}
}
