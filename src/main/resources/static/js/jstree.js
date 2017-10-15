$(function() {

	$('#jstree-categories').jstree({
		'core' : {
			'data' : {
				'url' : '/admin/tree',
				'dataType' : 'json',
				'data' : function (node) {
					return { 'id' : node.id };
				}
			},
			'force_text' : true,
			'check_callback' : true,
			'themes' : {
				'name' : 'proton',
				'icons' : false,
				'responsive' : true
			}
		},
		'plugins' : ['contextmenu' ]
	}).on('delete_node.jstree', function(e, data) {
		deleteCategory(data)
	}).on('create_node.jstree', function(e, data) {
		$.get('?operation=create_node', {
			'id' : data.node.parent,
			'position' : data.position,
			'text' : data.node.text
		}).done(function(d) {
			data.instance.set_id(data.node, d.id);
		}).fail(function() {
			data.instance.refresh();
		});
	}).on('rename_node.jstree', function(e, data) {
		addCategory(data);
	}).on('move_node.jstree', function(e, data) {
		$.get('?operation=move_node', {
			'id' : data.node.id,
			'parent' : data.parent,
			'position' : data.position
		}).fail(function() {
			data.instance.refresh();
		});
	}).on('copy_node.jstree', function(e, data) {
		$.get('?operation=copy_node', {
			'id' : data.original.id,
			'parent' : data.parent,
			'position' : data.position
		}).always(function() {
			data.instance.refresh();
		});
	}).on('changed.jstree', function(e, data) {
		//$('.jstree-rename-input').attr('maxLength', 50);
		if (data.node != null) {
			console.log(data.node.id);
			selectedNodeId = data.node.id;
			loadItems(data.node.id);
		}
	}).on('loaded.jstree', function() {
		console.log("Do something here...");
	});
});

function addCategory(data) {
	console.log("id: " + data.node.parent + " text: " + data.node.text
			+ " id: " + data.node.id);

	$.ajax({
		contentType : "application/json",
		type : 'POST',
		url : "/admin/category/" + data.node.parent + "/" + data.node.id + "/"
				+ data.node.text + "/addcategory",
		success : function(response, textStatus, jqXHR) {
			console.log("addCategory data: " + response);
			$.get('?operation=rename_node', {
				'id' : data.node.id,
				'text' : data.text
			}).fail(function() {
				data.instance.refresh();
			});

		},
		error : function(data, textStatus, jqXHR) {
			console.log("ERROR");
		}
	});
}

function deleteCategory(data) {
	console.log("id: " + data.node.parent + " text: " + data.node.text);
	if (confirm("Are you sure you want to delete " + data.node.text + "?")) {
		$.ajax({
			type : 'DELETE',
			url : "/admin/category/" + data.node.id,
			success : function(data, textStatus, jqXHR) {
				$.get('?operation=delete_node', {
					'id' : data.node.id
				}).fail(function() {
					data.instance.refresh();
				});
			},
			error : function(data, textStatus, jqXHR) {
				console.log("ERROR");
			}
		});
	}
}

function loadItems(categoryID) {
	$.ajax({
		contentType : "application/json",
		type : 'GET',
		url : "/admin/category/" + categoryID + "/items",
		success : function(data, textStatus, jqXHR) {
			console.log("SUCESS: " + textStatus + " Data: " + data);
			ChangeUrl("admin/items", "/admin/category/" + categoryID + "/items");
			$(".breadcrumb").html(data);
			$(".content").html(data);
			$('#jstree-categories').jstree('select_node', categoryID);

		},
		error : function(data, textStatus, jqXHR) {
			console.log("ERROR");
		}
	});
}

function ChangeUrl(page, url) {
	if (typeof (history.pushState) != "undefined") {
		var obj = {
			Page : page,
			Url : url
		};
		history.pushState(obj, obj.Page, obj.Url);
	} else {
		window.location.href = "homePage";
		// alert("Browser does not support HTML5.");
	}
}
