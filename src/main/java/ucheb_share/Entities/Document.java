package ucheb_share.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table("DOCUMENTS")
public class Document {
	@Id
	int id;
	@NotNull(message="Введите имя файла")
	String name;
	//String message;
	int authorId;
	int parentFolderId;
	//?Datetime uploadedAt;
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
