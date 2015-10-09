//alert("sas");
map = {1:'CSV file Input',2:'Google Analytics',3:'Generate Rows',4:'Table input',5:'CSV file Input'};
var count=0;
var val = 0;
function change(i){
    if(val==0)
    {
        val = val + 1;
        nice = document.getElementById(pas);
        pas.innerHTML = pas.innerHTML + "<div class=\"row\"  id=\""+ val + "\"> </div> <br>"
    }
    obj =document.getElementById(val);
    l =  "<div class=\"box0 col-md-2\">"  +
    "<button class=\"btn btn-primary btn-xs\" data-toggle=\"modal\" data-target=\"#myModal\">" +
                 map[i] +
                "</button>" +  " </div>";
    obj.innerHTML = obj.innerHTML + l;
}

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev,i) {
    ev.dataTransfer.setData("text/html", ev.target.id);
    count=i;
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text/html");
    ev.target.appendChild(document.getElementById(data));

}
function dropcopy(ev)
                    {
                    val = val + 1;
                    ev.preventDefault();
                    var data=ev.dataTransfer.getData("Text");
                    var copytext = document.createElement("P");
                    var original = document.getElementById(data);
                    var target = document.getElementById("pas")
                    p = "<div class=\"row\" id=\""+ val + "\">" +"<div class=\"box0 col-md-2\">"+"<button class=\"btn btn-primary btn-xs\" data-toggle=\"modal\" data-target=\"#myModal\">" +
                 map[count] +
                "</button>" +"</div></div>  <br>       ";     
                    target.innerHTML = target.innerHTML + p
                    ev.target.appendChild(copyimg);
                }
function clik(a){
    l = a.innerHTML;
    console.log(l);
    window.open("file:///home/sowmith/sem3/ssad_22/source/AdminLTE-master/csv_input.html");
}
