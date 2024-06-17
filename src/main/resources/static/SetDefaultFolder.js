function setDefaultFolder(folderId) {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/filesview/set-default-folder/" + folderId, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Default folder was set.");
        }
    };
    xhr.send();
}