  $(document).ready(function(){
     count = 0;
    $('.test').draggable({
       zIndex:3000,
           addClasses:'false',
           appendTo:'body',
           helper: "clone",
           start: function (e, u) {
       },
           stop: function () {
             
           }}).click(function(){
          var b=parseInt($(this.width));
        $(this).css('width',b+5)
        })
        $('#drop').droppable({
          accept: ".test",
          drop:function(e,u){
		          count = count + 1;
		
              var a=u.helper.clone();
              $(this).append(a);
              $(a).attr('class','dropped '+count).attr('onclick','connect_caller(event)').draggable({
                stop: function(e,u){
                  console.log($(this));
                  l = $(this);
                  p = $(l).attr('class');
                  p = p.split(' ');
              
                }
              });
          }  })
    })
  
function connect(divi1,divi2)
{
	if(count < 2)
	{
		return;
	}
	div1='.'+divi1;
	div2='.'+divi2;
	console.log($(div1));
	x11 = $(div1).position().left - 220 ;
	x22 = $(div2).position().left - 220 ;
	y11 = $(div1).position().top -75 ;
	y22 = $(div2).position().top -75;
	x11 = String(x11);
	x22 = String(x22);
	y11 = String(y11);
	y22 = String(y22);
	l = $('<svg><line x1="' + x11 + '" y1="' + y11 + '"x2="' + x22 +'"y2="' + y22 +'" class="' + divi1 + divi2 +'" style="stroke:rgb(255,0,0);stroke-width:2"></svg>');
  $('.tems').append(l);
}

   state = 0;
   x1 = "1";
   x2 = "2";
   function connect_caller(evt)
   {
      l = $(evt.target);
      l = $(l) .parent().attr('class').split(' ')[1];
      if(state == 0)
      {
        x1 = l;
        state = 1;
      }
      else if(state == 1)
      {
        x2 = l;
        state = 0;
        connect(x1,x2);
      }
   }

function forms(a)
{
  var asd = "<form>";
  var temp;
  for (var i in obj_csv)
  {
    asd += i+" : ";
    temp=obj[i];
    if(temp==1)
    {
      asd += "<input type=\"text\" />";
    }
    if(temp==0)
    {
      asd += "<input type=\"checkbox\" />";
    }
    asd += "<br>" 
  }
  asd += "</form>"
  document.getElementsByClassName(".modal-body").innerHTML = asd;
}

