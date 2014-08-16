var tumbler = {
	divHeight : 300,
	init : function(callback) {
		$.ajax({
			type: "GET",
			url: "/ajaxtest",
			data: { id: "test" }
		}).done(function( data ) {
			console.log(data);
			if(data !== "-1") {
				var temp = $("#test");
				var water = Math.floor(100-(data/11)*100);
				
				if(water>0) {
					$(temp).html(water + "%");
					$(temp).height(water*3).css({
					    backgroundColor: "#72A3D8"
					});
				}
				
			}
			
		});
	}
}

document.addEventListener("click", tumbler.init.bind(tumbler));
document.addEventListener("DOMContentLoaded", tumbler.init.bind(tumbler));