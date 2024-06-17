function showHidden(id) {
	const elementToShow = document.getElementById(id);
	if (elementToShow) {elementToShow.hidden = false;}
	else alert("Element with id=" + id + " wasn't find to show!");
}