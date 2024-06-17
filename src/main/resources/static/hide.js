function hide(id) {
	const elementToHide = document.getElementById(id);
	if (elementToHide) elementToHide.hidden = true;
	else alert("Element with id=" + id + " wasn't find to hide!");
}