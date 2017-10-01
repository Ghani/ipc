$(function() {
  $('#jstree-categories').jstree({
    'core' : {
      'data' : {
        "url" : "http://localhost:8080/admin/tree",
        "dataType" : "json" // needed only if you do not supply JSON headers
      },
      'themes': {
          'name': 'proton',
          'responsive': true
      }
    }
  });
});

$(function () {
    $('#jstree-categories').on('changed.jstree', function (e, data) {
        console.log(data.node.id);
        loadCategory(data.node.id);
    });
});


function loadCategory(categoryID) {
    $.ajax({
        contentType: "application/json",
        type: 'GET',
        url: "/admin/category/"+ categoryID + "/items",
        success: function (data, textStatus, jqXHR) {
        	console.log("SUCESS" + textStatus + " Data: " + data);
        	ChangeUrl("admin/items","/admin/category/"+ categoryID + "/items");
        	$(".content").html(data);
        	
        },
        error: function (data, textStatus, jqXHR) {
        	console.log("ERROR");
        }
    });
}

function ChangeUrl(page, url) {
    if (typeof (history.pushState) != "undefined") {
        var obj = {Page: page, Url: url};
        history.pushState(obj, obj.Page, obj.Url);
    } else {
        window.location.href = "homePage";
        // alert("Browser does not support HTML5.");
    }
}

$(document).ready(function () {
	console.log( "document loaded" );
    $('.delete-item').on("click", function(e){
        e.preventDefault();
    	var id = $(this).closest("td").attr("id");
    	console.log("id: " + id);
        if(confirm("Are you sure you want to delete the selected item?"  )){

            $.ajax({
                type:"DELETE",
                url:"/admin/deleteitem/" + id,
                success:function (data) {
                    $(".delete-item").closest("td#"+data).parent("tr").fadeOut("slow",function(){
                        $(".delete-item").closest("td#"+data).parent("tr").remove();
                    });
                }
            });
        }

    });
    
    $('.delete-document').on("click", function(e){
        e.preventDefault();
    	var id = $(this).closest("td").attr("id");
    	console.log("id: " + id);
        if(confirm("Are you sure you want to delete the selected document?")){

            $.ajax({
                type:"DELETE",
                url:"/admin/deletedocument/" + id,
                success:function (data) {
                	console.log("data: " + data);
                    $(".delete-item").closest("td#"+data).parent("tr").fadeOut("slow",function(){
                        $(".delete-document").closest("td#"+data).parent("tr").remove();
                    });
                }
            });
        }

    });

});




