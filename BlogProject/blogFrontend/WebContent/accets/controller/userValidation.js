app.controller("LogController",function($scope,$rootScope,$location,$http,$cookieStore){
	$scope.user={"email":"","password":""}
	$scope.validate=function(){
		console.log("validate user function")
		$http.post("http://localhost:8081/blogmiddleware/sign-in",$scope.user).then(function(response){
			$scope.message=response.statusText.message
			$cookieStore.put("userDetails",response.data)
			console.log($cookieStore.get("userDetails"));
			$rootScope.user=response.data
			$rootScope.currentUser=response.data
			if($rootScope.currentUser.role=="A"){
				$location.url("/index")
				alert($rootScope.currentUser.role)
			}
			if($rootScope.currentUser.role=="U"){
				$location.url("/addblog")
				alert($rootScope.currentUser.role)
			}
		},
		function(){
			alert("Invalid user please try again")
		}
		)
	}
})