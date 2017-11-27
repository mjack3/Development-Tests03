
package services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;
import domain.MailMessage;
import domain.Priority;
import domain.SpamWord;
import repositories.MailMessageRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class MailMessageService {

	//Repositories

	@Autowired
	private MailMessageRepository	mailMessageRepository;

	//Services

	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private FolderService			folderService;
	@Autowired
	private SpamWordService			spamWordService;
	@Autowired
	private LoginService			loginService;


	//Constructor

	public MailMessageService() {
		super();
	}

	//CRUD Methods

	public void send(String subject, String body, String priority, String recipient) {
		try {
			MailMessage mailMessage = new MailMessage();
			mailMessage.setSubject(subject);
			mailMessage.setSentMoment(new Date());
			mailMessage.setBody(body);

			Priority prio = new Priority();
			prio.setValue(priority);

			mailMessage.setPriority(prio);

			Actor from = mailMessageRepository.selectActorByMail(recipient, recipient);

			Assert.notNull(from);

			mailMessage.setRecipient(from);

			UserAccount userAccount = LoginService.getPrincipal();
			Actor by = mailMessageRepository.selectSelf(userAccount.getUsername());

			mailMessage.setSender(by);

			MailMessage saved = mailMessageRepository.save(mailMessage);

			for (Folder e : by.getFolders()) {
				if (e.getName().equalsIgnoreCase("outbox")) {
					e.getMailMessages().add(saved);
					folderService.save(e);

					break;
				}
			}

			boolean is_spam = false;
			String lower = body.toLowerCase();

			for (SpamWord e : spamWordService.findAll()) {
				if (lower.contains(e.getName().toLowerCase())) {
					is_spam = true;
					break;
				}
			}

			MailMessage saved_2 = mailMessageRepository.save(mailMessage);

			for (Folder e : from.getFolders()) {
				if (e.getName().equalsIgnoreCase("Inbox") && !is_spam) {
					e.getMailMessages().add(saved_2);
					folderService.save(e);

					break;
				}

				if (e.getName().equalsIgnoreCase("Spambox") && is_spam) {
					e.getMailMessages().add(saved_2);
					folderService.save(e);

					break;
				}
			}

		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public void moveTo(MailMessage m, Folder f) {
		Actor a = loginService.selectSelf();
		List<Folder> folders = (List<Folder>) a.getFolders();

		Folder in = null;

		for (Folder e : folders) {
			if (e.getMailMessages().contains(m)) {
				in = e;
				break;
			}
		}

		if (in != null) {
			in.getMailMessages().remove(m);
			List<MailMessage> messages = new LinkedList<MailMessage>(f.getMailMessages());
			messages.add(m);

			folderService.save(in);
			f.setMailMessages(messages);

			folderService.save(f);
		}
	}

	public void delete(Iterable<MailMessage> entities) {
		mailMessageRepository.delete(entities);
	}

	public MailMessage create() {
		MailMessage message = new MailMessage();

		message.setBody(new String());

		Priority priority = new Priority();
		priority.setValue("NEUTRAL");

		message.setPriority(priority);
		message.setRecipient(null);
		message.setSender(null);
		message.setSentMoment(new Date());

		return message;
	}

	public void delete(MailMessage entity) {
		Assert.notNull(entity);
		Actor a = loginService.selectSelf();
		List<Folder> folders = (List<Folder>) a.getFolders();

		Folder folder = null;

		for (Folder e : folders) {
			if (e.getMailMessages().contains(entity)) {
				folder = e;
				break;
			}
		}

		Assert.notNull(folder);

		if (folder.getName().equalsIgnoreCase("Trashbox")) {
			folder.getMailMessages().remove(entity);

			folderService.save(folder);
			mailMessageRepository.delete(entity);
		} else {
			for (Folder e : folders) {
				if (e.getName().equalsIgnoreCase("Trashbox")) {
					folder.getMailMessages().remove(entity);
					folderService.save(folder);
					folderService.flush();

					e.getMailMessages().add(entity);
					folderService.save(e);
					folderService.flush();

					break;
				}
			}
		}
	}

	public List<MailMessage> findAll() {
		return mailMessageRepository.findAll();
	}

	public MailMessage findOne(Integer arg0) {
		Assert.notNull(arg0);

		return mailMessageRepository.findOne(arg0);
	}

	public List<MailMessage> save(List<MailMessage> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());

		return mailMessageRepository.save(entities);
	}

	public MailMessage save(MailMessage arg0) {
		Assert.notNull(arg0);

		return mailMessageRepository.save(arg0);
	}

	//Other Methods

	public void flush() {
		mailMessageRepository.flush();
	}

	public List<Folder> messagesByFolder(int folder_id) {
		Assert.notNull(folder_id);

		return mailMessageRepository.messagesByFolder(folder_id);
	}

	public Double avgSystemFolders() {
		return mailMessageRepository.avgSystemFolders();
	}

	public Double avgSpamMessagesPerActor() {
		return mailMessageRepository.avgSpamMessagesPerActor();
	}

}
