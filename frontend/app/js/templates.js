angular.module("templates", []).run(["$templateCache", function($templateCache) {$templateCache.put("home.html","<h1 class=\"heading -large\">{{ home.title | ExampleFilter }}</h1>\n\n<h3 class=\"heading -medium\">Here is a fancy number served up courtesy of Angular: <span class=\"number-example\">{{ home.number }}</span></h3>\n\n<img src=\"images/angular.png\" height=\"100\" />\n<img src=\"images/gulp.png\" height=\"100\" />\n<img src=\"images/browserify.png\" height=\"100\" />\n\n<hr/>\n<div example-directive title=\"WOW!\" click-message=\"You clicked me!\">Directive is not loaded.</div>\n");
$templateCache.put("directives/example.html","<div class=\"example-directive\">\n  <h1>Directive title: {{title}}</h1>\n  <p>This is an example of a directive, click me!</p>\n</div>\n");}]);