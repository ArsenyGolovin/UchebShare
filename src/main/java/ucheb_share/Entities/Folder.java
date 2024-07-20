package ucheb_share.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table("FOLDERS")
public class Folder {
	@Id
	int id;
	//int flowId;
	//int flowYear;
	//short courseNum;
	
	@NotNull(message="Введите имя папки")
	String name;
	int authorId;
	int parentFolderId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getParentFolderId() {
		return parentFolderId;
	}
	public void setParentFolderId(int parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
}
