app.controller('regController',function($scope,$http,$location){
	
	$scope.user={"email":"","password":"","firstname":"","lastname":"","mobile":"","isOnline":false,"isActive":true};
	
	$scope.register=function(){
		
		console.log("Register function")
		$http.post('http://localhost:8081/blogmiddleware/registerUser',$scope.user).then(function(response){
			$scope.message=response.statusText.message;
			console.log('Status Text:'+$scope.message);																																		
			alert("User Registered Successfully Please Sign-in!")
			$location.url('/signin')
		})
	}
	
});