function some_number() {
  return Math.floor((Math.random()*6)+1);
}


function update_box(elem) {
  var box_id = $(elem).data("box");
  if (box_id) {
    $("#" + box_id).text("[" + some_number() +"]");
  }
}

function update_all() {
  $(".clickme").each(function(){update_box(this);});
}

function handle_click(e) {
  update_box(e.target);
}

var make_box = function() {
    var box_count = 0;

    return function() {
       var uniq_id    = "box-" + box_count++;
       var new_box    = $("<span>[-]</span>").attr("id", uniq_id);
       var new_button = $("<button class='clickme' type='button'>").data("box", uniq_id)
                                                                   .text(uniq_id)
                                                                   .click(handle_click);

       $("body").append(new_box)
                .append(new_button)
                .append("<br/>");
    }
}();

$(document).ready(function() {
  $("h1.title").text("javascript/jquery example");

  $(".addbox").click(make_box);
  $(".updateall").click(update_all);
});

