package ucheb_share.Repositories;

import org.springframework.data.repository.CrudRepository;

import ucheb_share.Entities.Document;

public interface DocumentRepository extends CrudRepository<Document, Integer> {
	
	public Iterable<Document> findByParentFolderId(int parentFolderId);
}
