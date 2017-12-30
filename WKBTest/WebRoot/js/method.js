function $(select){
	if(typeof select == 'string'){
		let selector =select.trim();
		let firstChar = selector.charAt(0);
		if (firstChar =='#') {
			return document.getElementById(selector.substring(1)/*截取从一开始到结束*/);
		}else if (firstChar == '.') {
			return document.getElementsByClassName(selector.substring(1));
		}
		else if(/^[a-zA-Z][A-Za-z1-6]{0,6}$/.test(selector)){//正则表达式
			return document.getElementsByTagName(selector);
		}
		else if(/^<[a-zA-Z][A-Za-z1-6]{0,6}>$/.test(selector)){//正则表达式
			return document.createElement(selector.slice(1,-1));
		}
	}
	else if(typeof select == 'function'){
		window.onload=function(){
			select();
		}
	}
}

function oppend(parentNode,Child){
	parentNode.appendChild(Child);
}

function prepend(parentNode,Child){
	let firstChild = parentNode.firstElementChild;
	if(firstChild){
		return parentNode.insertBefore(Child,firstChild);
	}else{
		return parentNode.appendChild(Child);
	}
}

HTMLElement.prototype.append = function(child){
	this.appendChild(child);
}
HTMLElement.prototype.prepend = function(Child){
	let firstChild = this.firstElementChild;
	if(firstChild){
		return this.insertBefore(Child,firstChild);
	}else{
		return this.appendChild(Child);
	}
}

HTMLElement.prototype.appendTo = function(parentNode){
	parentNode.appendChild(this);
}

HTMLElement.prototype.prependTo = function(parentNode){
	parentNode.prepend(this);
}

HTMLElement.prototype.insert = function(node){
	let parent = this.parentNode;
	parent.insertBefore(node,this);
}

HTMLElement.prototype.after = function(node){
	let next = this.nextElementSibling;
	if (next){
		next.insert(node);
	}else{
		let parent = this.parentNode;
		parent.append(node);
	}
}

HTMLElement.prototype.parent = function(){
	return this.parentNode;
}


HTMLElement.prototype.parents = function(){
	let arr = []; 
	let parent = this.parentNode; 
	if(parent.nodeName == 'BODY'){
		return this.parentElement;
	}
	while(parent.nodeName != 'HTML'){
		arr.push(parent);
		parent = parent.parentNode;
		if(parent.nodeName == 'HTML'){
			arr.push(parent);
		}
	}
	return arr;
}

HTMLElement.prototype.offsetParents = function(){
	let parent = this.parents();
	let node = null;
	for (let i = 0; i < parent.length; i++) {
		let pos = window.getComputedStyle(parent[i],null).position;
		if (pos == 'relative' || pos == 'absolute') {
			node = parent[i];
			break;
		}else{
			node = document.body;
		}
	}
	return node;
}

HTMLElement.prototype.next = function(){
	let next = this.nextElementsibling;
}

class Float{
	constructor(obj){
		this.obj = obj;
		this.speedY = 10;
		this.mxh = window.innerHeight - this.obj.offsetHeight;
		this.mxw = window.innerWidth - this.obj.offsetWidth;
	}
	start(){
		this.move();
	}
	move(){
		let _this = this;
		this.t=setInterval(function(){
			let tops = _this.obj.offsetTop + _this.speedY;
			if(tops >= _this.mxh){
				tops = _this.mxh;
				_this.speedY *= -1;
			}
			if(0 >= tops){
				tops = 0;
				_this.speedY *= -1;
			}
			_this.obj.style.top = tops + 'px';
		}, 100);
	}
	stop(){
        clearInterval(this.t);
    }
    resize(){
    	this.mxh = window.innerHeight - this.obj.offsetHeight;
    }
}