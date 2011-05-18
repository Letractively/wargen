/**
 * 
 */
function abrirModal(pagina)
{
	document.getElementById("modal").style.visibility = "visible";
	document.getElementById("fundoModal").style.visibility = "visible";
	document.getElementById("ifModal").src = pagina;
}

function fecharModal()
{
	document.getElementById("modal").style.visibility = "hidden";
	document.getElementById("fundoModal").style.visibility = "hidden";
}