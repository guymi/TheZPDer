var req = require('querystring');
var fs = require('fs');
module.exports = function (file) {
        fs.readFile(file.path, 'utf8', function (err, data) {
                if (err) {
                    return console.log(err);
                }
                //ToDo: Call the parse function
                console.log(data);
                req.post(http: //127.0.0.1:8080/user)

                    var options = {
                        uri: 'https://www.googleapis.com/urlshortener/v1/url',
                        method: 'POST',
                        json: {"userId":2,"userName":"Guy2","userEmail":"Guy2@someMail.com","tags":["tag11","tag12","tag13"]}
                    };

                    request(options, function (error, response, body) {
                        if (!error && response.statusCode == 200) {
                            console.log(body.id) 
                        }
                    });
                });
        }