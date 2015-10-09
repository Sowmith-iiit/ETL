var app = angular.module('ngAppDemo', ['ngDialog']);
app.controller('project',function(){
	this.test=""
});
app.directive('logoLinks',function(){
	return {
		restrict : 'E',
		templateUrl : "logo-links.html"
	};
});
app.directive('navBar',function(){
	return {
		restrict : 'E',
		templateUrl : "nav-bar.html"
	};
});
app.directive('leftSide',function(){
	return {
		restrict : 'E',
		templateUrl : "left-side.html"
	};
});
app.directive('rightSide',function(){
	return {
		restrict : 'E',
		templateUrl : 'right-side.html'
	}
});
app.controller('sideBar',function($element){
	this.tab = 0;
	this.setTab = function(arg){
		this.tab = arg;
		($element);
		
	};
	this.isSet = function(arg){
		return this.tab == arg;
	};
});
app.controller('PopupCtrl',function($scope, ngDialog){
	
	$scope.clickToOpen = function () {
        ngDialog.open({ template: 'csv_input.html' });
    };
});