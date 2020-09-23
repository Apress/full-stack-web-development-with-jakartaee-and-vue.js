export class Image {
    imageUrl: string = ''

    static emptyImage () {
      let image:Image = new Image()

      return image
    }

    static newImage (imageUrl: string) {
      let image:Image = new Image()

      image.imageUrl = imageUrl

      return image
    }

    copyImage (imageToCopy: Image) {
      this.imageUrl = imageToCopy.imageUrl
    }
}
