function getSiblingElement(node){
	if(node.nextElementSibling){
		getSiblingElement = function(node){
								return node.nextElementSibling;
							};
	}else{
		getSiblingElement = function(node){
								while((node = node.nextSibling) && node.nodeType != 1){};
								return node;
							};
	}
	return getSiblingElement(node);
}
				
function toggleContentDisplay(event){
	if(document.all){
		toggleContentDisplay = 	function(event){
									var nodeStyle = getSiblingElement( getSiblingElement(window.event.srcElement.parentNode) ).style;
									nodeStyle.display = nodeStyle.display == "block" ? "none" : "block";
								};
	}else{
		toggleContentDisplay = 	function(event){
									var nodeStyle = getSiblingElement( getSiblingElement(event.target.parentNode) ).style;
									nodeStyle.display = nodeStyle.display == "block" ? "none" : "block";
								};
	}
	toggleContentDisplay(event);
}