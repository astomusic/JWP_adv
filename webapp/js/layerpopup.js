jQuery(function($) {
	function layer_open(el) {
		// $('.layer').addClass('open');
		$('.layer').fadeIn();
		var temp = $('#' + el);
		console.log(temp);
		console.log(temp[0].clientWidth);
		console.log($(document).width());
		if (temp.outerHeight() <= $(document).height())
			temp.css('top', ($(document).height() - temp[0].clientHeight) / 2 + 'px');
		else
			temp.css('top', '0px');
		if (temp.outerWidth() <= $(document).width())
			temp.css('left', ($(document).width() - temp[0].clientWidth) / 2 + 'px');
		else
			temp.css('left', '0px');
	}
	$('#layer_open').click(function() {
		layer_open('layer1'); /* 열고자 하는 것의 아이디를 입력 */
		return false;
	});
	$('.layer .bg').click(function() {
		$('.layer').fadeOut();
	});
	$('#layer_close').click(function() {
		$('.layer').fadeOut();
		return false;
	});
	$(document).ready(function() {
	});
});