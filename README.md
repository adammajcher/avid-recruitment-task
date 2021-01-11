# Demo Json Application

### Running application
* Requires docker
* Build docker image from this repository working directory
```docker build -t demo-app .``` 
* Run application with image
```docker run -p 8081:8080 demo-app```
* Go to ```http://localhost:8081``` and see HTTP response.
