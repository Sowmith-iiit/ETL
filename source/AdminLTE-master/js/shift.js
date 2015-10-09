//alert("sas");
map = {1:'General',2:'Icons',3:'Buttons',4:'Sliders',5:'Timeline'};
var count=0;
function change(i){
obj =document.getElementById(i);
//obj.onclick=function(){
var target = document.getElementById("pas");// find the list-item
target.innerHTML += ""+map[i] +"         "; // set it's content

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
                    ev.preventDefault();
                    var data=ev.dataTransfer.getData("Text");
                    var copytext = document.createElement("P");
                    var original = document.getElementById(data);
var target = document.getElementById("pas")
target.innerHTML += "<br />"+ "<br />"+ "<br />"+map[count] +"         ";
          
                    ev.target.appendChild(copyimg);

                    }
