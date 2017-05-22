function selectCB() {
	var selected = $('input[name!="ignore"]:checked').map(function() {
	    return this.value;
	}).get().join(",");	
	
	window.location = "simulator?selected=" + selected; 
}

function selectVirt() {
	var selected = $("input[type='radio']:checked").map(function() {
	    return this.name;
	}).get().join(",");	
	
	if (selected.split(',').length != 7) {
		alert("Odabrani igrači: " + selected + "\nOdaberite i ostale!");
	} else {
		selected = $("input[type='radio']:checked").map(function() {
		    return this.value;
		}).get().join(",");	
		window.location = "potvrdiOdabirVirt?selected=" + selected; 
	}
}


