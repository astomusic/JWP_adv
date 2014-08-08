jQuery(function($) {
	function layer_open(el) {
		$('.layer').fadeIn();
		var temp = $('#' + el);
		if (temp.outerHeight() <= $(document).height())
			temp.css('top', ($(document).height() - temp[0].clientHeight) / 2 + 'px');
		else
			temp.css('top', '0px');
		if (temp.outerWidth() <= $(document).width())
			temp.css('left', ($(document).width() - temp[0].clientWidth) / 2 + 'px');
		else
			temp.css('left', '0px');
	}
	$('#layer_open1').click(function() {
		layer_open('layer1'); /* 열고자 하는 것의 아이디를 입력 */
		return false;
	});
	
	$('#layer_open2').click(function() {
		layer_open('layer1'); /* 열고자 하는 것의 아이디를 입력 */
		return false;
	});
	
	$('.bg').click(function() {
		$('.layer').fadeOut();
		$('.layer').fadeOut();
	});
	
	$('#layer1_close').click(function() {
		$('.layer').fadeOut();
		return false;
	});
	
	$('#layer2_close').click(function() {
		$('.layer').fadeOut();
		return false;
	});
	
	$(document).ready(function() {
	});
});