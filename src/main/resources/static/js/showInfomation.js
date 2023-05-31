$(function() {
	$("#infomationButton").on('click change', function() {
		var institutionsRadio = document.getElementById('institutions')
		var priceRadio = document.getElementById('price')
		var crimesRadio = document.getElementById('crimess')
		if (institutionsRadio.checked) {
			$.ajax({
				url: 'http://localhost:8080/',
				type: 'post',
				dataType: "json",
				data,
				success: function(data) { }
			})
		}
		if (priceRadio.checked) {
			$.ajax({
				url: 'http://localhost:8080/',
				type: 'post',
				dataType: "json",
				data,
				success: function(data) { }
			})
		}
		if (crimesRadio.checked) {
			$.ajax({
				url: 'http://localhost:8080/',
				type: 'post',
				dataType: "json",
				data,
				success: function(data) { }
			})
		}
	})
})