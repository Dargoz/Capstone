package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class ScoreResponse(
    @field:SerializedName("overall")
    val overall: Int,

    @field:SerializedName("story")
    val story: Int,

    @field:SerializedName("animation")
    val animation: Int,

    @field:SerializedName("sound")
    val sound: Int,

    @field:SerializedName("character")
    val character: Int,

    @field:SerializedName("enjoyment")
    val enjoyment: Int,
)