package ucheb_share.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import ucheb_share.Entities.Document;
import ucheb_share.Entities.Folder;
import ucheb_share.Entities.User;
import ucheb_share.Repositories.DocumentRepository;
import ucheb_share.Repositories.FolderRepository;
import ucheb_share.Repositories.UserRepository;

import java.nio.file.Paths;
@Controller
@RequestMapping("/filesview")
@SessionAttributes({"user", "parentFolders"})
public class DocumentController {
	
	DocumentRepository docRepo;
	FolderRepository folderRepo;
	UserRepository userRepo;
	
	Path pathToDataFiles = Paths.get(System.getProperty("user.dir"));
	
	@GetMapping
	public String showDocuments(Model model) {
		model.addAttribute("documents", docRepo.findByParentFolderId(this.getCurrentFolderId(model)));
		model.addAttribute("folders", folderRepo.findByParentFolderId(this.getCurrentFolderId(model)));
		model.addAttribute("currentFolderId", ((ArrayDeque<Folder>)model.getAttribute("parentFolders")).getLast().getId());
		return "filesview";
	}
	
	
	@GetMapping("/next")
	public String nextFolder(Model model, @RequestParam(value="s") int selectedFolderId, @ModelAttribute("parentFolders") ArrayDeque<Folder> parentFolders) {
		if (selectedFolderId == -1) {
			if (parentFolders.size() == 1) return "redirect:/home";
			parentFolders.removeLast();
		} else {
			//  Removal of folders in ParentFolders behind selected folder in folder path
			Iterator<Folder> folderIter = parentFolders.iterator();
			int end = -1;
			for (int i = 0; i < parentFolders.size() && end == -1; i++) {
				Folder folder = folderIter.next();
				if (folder.getId() == selectedFolderId)
					end = i;
			}
			if (end != -1)
				for (int i = parentFolders.size(); i > end + 1; i--)
					parentFolders.removeLast();
			else parentFolders.add(this.folderRepo.findById(selectedFolderId).get());
		}
		model.addAttribute("parentFolders", parentFolders);
		return "redirect:/filesview";
	}
	
	
	@PostMapping("new-folder")
	public String addFolder(Model model, @ModelAttribute("newFolder") Folder newFolder, @ModelAttribute("user") User user) {
		newFolder.setParentFolderId(this.getCurrentFolderId(model));
		newFolder.setAuthorId(user.getId());
		this.folderRepo.save(newFolder);
		return "redirect:/filesview";
	}
	
	
	@PostMapping("new-document")
	public String addDocument(Model model, @RequestParam("file") MultipartFile file, @ModelAttribute("newDocument") Document newDocument, @ModelAttribute("user") User user) throws IOException {
		newDocument.setName(file.getOriginalFilename());
		newDocument.setParentFolderId(this.getCurrentFolderId(model));
		newDocument.setAuthorId(user.getId());
		this.docRepo.save(newDocument);
		file.transferTo(new File(this.pathToDataFiles.resolve("DATAFILES/" + newDocument.getId()).toString()));
		return "redirect:/filesview";
	}
	
	
	@PostMapping("/set-default-folder/{folderId}")
	@ResponseBody
	public String setDefaultFolder(Model model, @PathVariable int folderId, @ModelAttribute User user) {
		user.setDefaultFolderId(folderId);
		userRepo.save(user);
		model.addAttribute(user);
		return "Success";
	}
	
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Resource> showFile(@PathVariable int id) {
	    File file = new File(this.pathToDataFiles.resolve("DATAFILES/" + id).toString());
	    HttpHeaders headers = new HttpHeaders();
	    String filename = this.docRepo.findById(id).get().getName();

	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(new FileSystemResource(file));
	}
	
	
	public DocumentController(DocumentRepository docRepo, FolderRepository folderRepo, UserRepository userRepo) {
        this.docRepo = docRepo;
        this.folderRepo = folderRepo;
        this.userRepo = userRepo;
    }
	
	
	@ModelAttribute("parentFolders")
	public ArrayDeque<Folder> parentFolders() {
		ArrayDeque<Folder> parentFolders = new ArrayDeque<Folder>();
		parentFolders.addLast(this.folderRepo.findById(0).get());
		return parentFolders;
	}
	

	private int getCurrentFolderId(Model model) {
		return ((ArrayDeque<Folder>)model.getAttribute("parentFolders")).getLast().getId();
	}
	
	
	@ModelAttribute("newFolder")
	public Folder folder() {
		return new Folder();
	}
	
	
	@ModelAttribute("newDocument")
	public Document document() {
		return new Document();
	}
}
