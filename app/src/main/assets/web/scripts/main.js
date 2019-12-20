

let image = document.querySelector('img');
image.onclick = function() {
	let src = image.getAttribute('src');
	image.setAttribute('src', 'images/nezha-2.gif');
}

function setHeading(name) {
  let myHeading = document.querySelector('h1');
  myHeading.textContent = 'Mozilla 酷毙了，' + name + '！';
}

function setUserName() {
  let myName = prompt('请输入你的名字');
  localStorage.setItem('name', myName);
  setHeading(myName);
} 

let storedName = localStorage.getItem('name');
if(!storedName) {
   setUserName();
} else {
   setHeading(storedName);
}


let myHeading = document.querySelector('h1');
myHeading.textContent = 'Hello world!';

const randomize = document.querySelector('.randomize');
const story = document.querySelector('.story');
const storyTextZh = '外边有34度';
randomize.addEventListener('click', result);
function result() {
	story.textContent = storyTextZh;
	story.style.visibility = 'visible';
}

var select = document.querySelector("select");
var list = document.querySelector("ul");

select.onchange = function(){
	var choice = select.value;
	var days  = 31;
	if(choice == "February") {
		days = 28;
	}
	create(days);
}

function create(days){
	list.innerHTML = "";
	for(var i = 1; i <= days; i++){
		var listItem = document.createElement('li');
		listItem.textContent = i;
		list.appendChild(listItem);
		
	}
}
