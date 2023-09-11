	$('#com-toggle').hide();
    $('#com').mouseover(function() {
        $('#com-toggle').show();
    })
	$('#com').mouseleave(function() {
        $('#com-toggle').hide();
    })
	$('#com-toggle').mouseover(function() {
		$('#com-toggle').show();
	})
	$('#com-toggle').mouseleave(function() {
		$('#com-toggle').hide();
	})

	$('#shop-toggle').hide();
    $('#shop').mouseover(function() {
        $('#shop-toggle').show();
    })
	$('#shop').mouseleave(function() {
        $('#shop-toggle').hide();
    })
	$('#shop-toggle').mouseover(function() {
		$('#shop-toggle').show();
	})
	$('#shop-toggle').mouseleave(function() {
		$('#shop-toggle').hide();
	})
	$('#shop').click(()=> {
		location.href = "/shopping/list";
	})