
const express = require('express')
const app = express()
const port = 1001
var serverMD5;

app.get('/latest', function(req, res){
  const file = `${__dirname}/files/server.jar`;
  res.download(file); // Set disposition and send it.
  console.log(`[Informations] >> server.jar file has been requested and delivered. \n`)

});
app.listen(port, () => {
  console.log(`[Informations] >> UplaCraft is now running @http://localhost:${port} \n`)
    console.log("[Informations] >> Retriving server file MD5 for futher needs... \n")
  var exec = require('child_process').exec;
var child;

child = exec("md5sum ./files/serverr.jar",
   function (error, stdout, stderr) {
       console.log("[Operation Information] >> Server file MD5 retrived ! (Operation sucessful)")
      console.log("[Terminal Output] >> The server's MD5 is : " + stdout.replace("./files/server.jar" , ""));
      serverMD5 = stdout.replace("./files/server.jar" , "");
      if (error !== null) {
          console.log("[Error] >> Can't execute command using child_process you might need to restart your computer or check if the process was launched as root (Error : 404 | 502)" + error);
      }
   });
})


app.get('/', (req, res) => {
  res.send(`The server's MD5 is : ${serverMD5}`);
})
