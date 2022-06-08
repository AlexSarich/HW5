sealed class Attachments (val type: String) {
    data class PhotoAttachment(val photo: Photo): Attachments("Photo")
    data class VideoAttachment(val video: Video): Attachments("Videos")
    data class AudioAttachment(val audio: Audio): Attachments("Audio")
    data class DocAttachment(val document: Document) : Attachments("Document")
    data class LinkAttachment(val link: Link) : Attachments("Link")
}

data class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val userId: Int = 0,
    val text: String = "text",
    val date: Int = 20122012,
    val sizes: String = "640*480",
    val height: Int = 480,
    val width: Int = 640
        )

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "name",
    val description: String = "description",
    val duration: Int = 0,
    val date: Int = 20122012,
    val addingDate: Int = 20122012,
    val views: Int = 0
)

data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val artist: String = "artist",
    val title: String = "title",
    val duration: Int = 0,
    val url: String = "url",
    val lyricsId: Int = 0,
    val albumId: Int = 0,
    val genreId: Int = 0
)

data class Document(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "title",
    val size: Int = 0,
    val ext: String = "ext",
    val url: String = "url",
    val date: Int = 20122012,
    val type: Int = 1
)
data class Link(
    val url: String = "url",
    val title: String = "title",
    val caption: String = "caption",
    val description: String = "description",
    val previewPage: String = "page",
    val previewUrl: String = "url"
)