package org.parthinfotech.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailPropertiesConfig {

	private String hostName;
	private int port;
	private String from;
}
