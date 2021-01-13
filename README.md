# Demo Json Application

### Running application
* Requires docker
* Build docker image from this repository working directory
```docker build -t demo-app .``` 
* Run application with image
```docker run -p 8080:8080 demo-app```
* Go to ```http://localhost:8080``` and see HTTP response.

### Application features included:
- GET / 
  * query (pass an optional search string for looking up list by path)
  * skip (number of records to skip for pagination)
  * limit (maximum number of records to return)
- GET / {folderId}
  * depends on folderId format can take:
    * numeric eg. /15
    * folder path (path should be URL encoded) eg. /%2F%2Ftest-path%2FCloudUX1%2Fproj1%2Fproj1%20Bin.avb
    * folder name eg. /SequTest Bin1
  * query (filtering of folder content by file type in base->type)
  * skip (number of records to skip for pagination)
  * limit (maximum number of records to return) 
### Additional features:
* Dockerfile used for creation of docker container
* CI(Continuous integration) with free gitHub action
