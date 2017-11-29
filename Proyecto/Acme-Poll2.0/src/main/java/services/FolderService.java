
package services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;
import domain.MailMessage;
import repositories.FolderRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class FolderService {

	@Autowired
	private FolderRepository		folderRepository;

	//Services
	@Autowired
	private AdministratorService	administratorService;


	@Autowired
	private MailMessageService		mailmessageService;


	public Folder create() {
		Folder folder = new Folder();

		folder.setName(new String());
		folder.setMailMessages(new ArrayList<MailMessage>());

		return folder;
	}

	public List<Folder> createDefaultFolders() {
		List<Folder> folders = new ArrayList<Folder>();

		Folder inbox = create();
		inbox.setName("Inbox");
		inbox.setMailMessages(new LinkedList<MailMessage>());

		Folder outbox = create();
		outbox.setName("Outbox");
		outbox.setMailMessages(new LinkedList<MailMessage>());

		Folder trashbox = create();
		trashbox.setName("Trashbox");
		trashbox.setMailMessages(new LinkedList<MailMessage>());

		Folder spambox = create();
		spambox.setName("Spambox");
		spambox.setMailMessages(new LinkedList<MailMessage>());

		folders.add(inbox);
		folders.add(outbox);
		folders.add(trashbox);
		folders.add(spambox);

		return folders;
	}

	public Actor selectByUsername(String username) {
		return folderRepository.selectByUsername(username);
	}

	public Folder saveCreate(Folder folder) {
		Assert.notNull(folder);
		Assert.isTrue(!folder.getName().equals("inbox") && !folder.getName().equals("outbox")
			&& !folder.getName().equals("trashbox") && !folder.getName().equals("spambox"));

		Folder saved = folderRepository.save(folder);
		UserAccount userAccount = LoginService.getPrincipal();

		Actor actor = folderRepository.selectByUsername(userAccount.getUsername());
		actor.getFolders().add(saved);

		
		return saved;
	}

	public Folder save(Folder entity) {
		return folderRepository.save(entity);
	}

	public List<Folder> save(Iterable<Folder> entities) {
		return folderRepository.save(entities);
	}

	public void delete(Folder entity) {
		Assert.notNull(entity);

		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = folderRepository.selectByUsername(userAccount.getUsername());
		actor.getFolders().remove(entity);
		
		mailmessageService.delete(entity.getMailMessages());
		folderRepository.delete(entity);
	}

	public void delete(Iterable<Folder> entities) {
		folderRepository.delete(entities);
	}

	public void flush() {
		folderRepository.flush();
	}

	public Folder findOne(Integer id) {
		return folderRepository.findOne(id);
	}

	public Double avgFoldersPerActor() {
		return folderRepository.avgFoldersPerActor();
	}

}
