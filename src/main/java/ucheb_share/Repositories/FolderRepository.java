package ucheb_share.Repositories;

import org.springframework.data.repository.CrudRepository;

import ucheb_share.Entities.Folder;

public interface FolderRepository extends CrudRepository<Folder, Integer> {
	
	public Iterable<Folder> findByParentFolderId(int parentFolderId);
}
