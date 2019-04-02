package org.podcastpedia.batch.jobs.addpodcast.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.podcastpedia.batch.common.listeners.LogProcessListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class EmailNotificationServiceImpl implements EmailNotificationService {

	private static final Log log = LogFactory.getLog(EmailNotificationServiceImpl.class);
	
	@Inject
    private JavaMailSender mailSender;
	
	@Inject
    private VelocityEngine velocityEngine;

	public void sendPodcastAdditionConfirmation(final String name, final String email, final String podcastUrl) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {


			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setTo("xxx@gmail.com");
	             message.setBcc("xxx@gmail.com");
			     message.setFrom("xxx@gmail.com");
			     message.setSubject("Your podcast has been added to Podcastpedia.org");
			     message.setSentDate(new Date());
			     
			     Map model = new HashMap();	             
			     model.put("name", name);
			     model.put("podcastUrl", podcastUrl);
			     
			     String text = VelocityEngineUtils.mergeTemplateIntoString(
			        velocityEngine, "templates/podcast_addition_notification.vm", "UTF-8", model);
			     
			     message.setText(text, true);
			     //IMPORTANT - see documentation for setText
//			     message.addInline("fb_logo", new ClassPathResource("img/fb_51.png"));
//			     message.addInline("twitter_logo", new ClassPathResource("img/twitter_51.png"));
//			     message.addInline("gplus_logo", new ClassPathResource("img/gplus_51.png"));	
				
			}
		};
		       log.info("Sending email with podcaseUrl: "+podcastUrl);
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

