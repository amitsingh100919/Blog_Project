var app= angular.module("demo",['ngRoute','ngCookies'])
		app.config(function($routeProvider){
			$routeProvider
			.when("/register" ,{
				templateUrl:"register.html"
			})
			.when("/home" ,{controller:"homeCtrl",
				templateUrl:"home.html"
			})
			.when("/signin",{
				templateUrl:"sign-in.html"
			})
			.when("/addblog",{
				templateUrl:"addBlog.html"
			})
			.when("/showAllBlog",{
				templateUrl:"ShowAllBlog.html"
			})
			.when("/showBlog",{
				templateUrl:"showBlog.html"
			})
			.when("/addjob",{
				templateUrl:"AddJob.html"
			})
			.when("/blogswaitingforapproval",{
				templateUrl:"BlogsWaitingForApproval.html"
			})
			.when("/blogsdetail",{
				templateUrl:"BlogDetail.html"
			})
			.when("/showalljob",{
				templateUrl:"ShowAllJob.html"
			}).when("/notification",{controller:"notificationCtrl",
				templateUrl:"notificationDetail.html"
			})
			.otherwise({templateUrl:"sign-in.html"})
			
	
		})
		
				
app.run(function($rootScope,$cookieStore,$http,$location){
	if($rootScope.user==undefined)
		$rootScope.user=$cookieStore.get("userDetails")
		
		$rootScope.logout = function(){
		$http.post("http://localhost:8081/blogmiddleware/sign-out").then(function(response){
			delete $rootScope.user
			$cookieStore.remove("userDetails")
			$location.url("/signin")
		},function(response){
			if($rootScope.user!=undefined)
			delete $rootScope.user
			$cookieStore.remove("userDetails")
			$location.url("/signin")
		}
		)
	}
})