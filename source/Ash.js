
$('.treeview').click(function(){
      l = $(this).attr('class');
      l = l.split(' ');
      if(l[1]=="active")
      {
         l = this;  
         $(this).removeClass('active');
         p = $(this).find('ul');
         $(p).css('display','None');
      }
      else
      {
         $(this).addClass('active');
         $(this).find('ul').css('display','block');
      }
});
