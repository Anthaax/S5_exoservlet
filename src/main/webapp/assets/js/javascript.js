function validate() {

	var close = window.confirm("Voulez-vous vraiment supprimer cette utilisateur ?")
	if (close)
	   return true;
	else
	  return false;
}