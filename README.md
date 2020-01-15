# Cat_API

REST application for download images from https://thecatapi.com/

To use application send a json request with post-method to localhost:8082/cats

App will return set of paths to downloaded files

Request example below will return 5 bengal cats pictures .jpg or .gif format: 

{"mimeTypes":["jpg","gif"],
	"breedId":"beng",
	"limit":5}

Empty json request {} will return one random picture

See https://docs.thecatapi.com/ for more information about request parameters
