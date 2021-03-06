package org.podcastpedia.batch.jobs.notifysubscribers.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.podcastpedia.batch.common.entities.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class EmailNotificationServiceImpl implements EmailNotificationService {
	
	@Inject
    private JavaMailSender mailSender;
	
	@Inject
    private VelocityEngine velocityEngine;

	public void sendNewEpisodesNotification(final User emailSubscriber) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
								
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setTo("xxx@gmail.com");
	             message.setBcc("xxx@gmail.com");
			     message.setFrom("xxx@gmail.com");
			     
			     message.setSubject("Latest episodes from your podcast subscriptions");//or maybe name some + "and more", like you tube
			     message.setSentDate(new Date());
			     
			     Map model = new HashMap();	
			     model.put("user", emailSubscriber);
			     
			     //set today's date
			     Date now = new Date();
			     SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
			     model.put("today", dateFormat.format(now));
			     
			     String text = VelocityEngineUtils.mergeTemplateIntoString(
			        velocityEngine, "templates/new_episodes_table.vm", "UTF-8", model);
			     
			     message.setText(text, true);
				 System.out.println(text);
			}
		};
		       this.mailSender.send(preparator);			
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
}

