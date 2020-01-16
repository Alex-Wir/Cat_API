# Cat_API

REST application for download images from https://thecatapi.com/

To use application send a POST request to [localhost:8082/cats](localhost:8082/cats)</br>
Request should contain parameters in json format 

App will return set of paths to downloaded files</br>
Request example below will return 5 bengal cats pictures .jpg or .gif format: 

```
{"mimeTypes": ["jpg","gif"],
 "breedId": "beng",
 "limit": 5}
```

Empty json request {} will return one random picture

See https://docs.thecatapi.com/ for more information about request parameters
