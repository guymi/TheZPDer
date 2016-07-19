
var scoreDictionary = {
    angular : [{
        match :  '\\$scope' ,
        score : 0.001
    },{
        match : 'ng-controller|ng-model|ng-bind|ng-app',
        score:0.01
    }],
    react : [ {
        match :"require('react')",
        score : 5
    }, {
        match :'import\\s+[\\W\\w]+from\\s[\'"]react["\']',
        score :3
    }],
    css:[{
        match:'([\\.#]?\\w)+{\\s(.+:.+;+\\s)+}' ,
        score: 0.01
    }],
    'ui.router' :[{ match: 'ui.router' , score: 225 }],
    // 'templates':[{ match: 'templates' , score: 20 }],
    'app.filters':[{ match: '(\d\w\W)+.filter\('  , score: 125 }],
    'app.controllers':[{ match: '(\d\w\W)+.controller\('  , score: 125 }],
    'app.services':[{ match:'(\d\w\W)+.service\('  , score: 125 }],
    'app.directives':[{ match: '(\d\w\W)+.directive\(' , score: 225 }]
};

function buildDictionary(){
    var rtn = {};
    for(var i in scoreDictionary){
        rtn[i] =  scoreDictionary[i].map(function(obj){
            return   function (string){
                string  = string || "" ;
                var regexp = new RegExp(obj.match,"g");
                var  n = (string.match(regexp) || []).length ;
                return (n*obj.score);
            };
        });
    }
    return rtn;
}

var dictionary = buildDictionary();

var weightCalc =  function(string){
    string = string || '';
    var total = 0 , score =[];
    for(var i in  dictionary){
        var count =  dictionary[i].map(function(calc){
                return calc(string);
            })
            .reduce(function(total, num) {
                return total + num;
            },0);
        score.push({
            label : i,
            count:count
        });
        // total += count;
    }

    return score.filter(function(obj){
        return obj.count;
    }).sort(function (a,b) {
        return b.count - a.count;
    }).splice(0, 3).map(function (obj) {
        return obj.label;
    });
    // return {
    //     total :  total ,
    //     data : score
    // };
};

module.exports =  weightCalc;