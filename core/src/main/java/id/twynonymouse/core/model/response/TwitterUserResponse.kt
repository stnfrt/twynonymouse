package id.twynonymouse.core.model.response

data class TwitterUserResponse(
    val verified: Boolean? = null,
    val description: String? = null,
    val friends_count: Int? = null,
    val suspended: Boolean? = null,
    val id_str: String? = null,
    val profile_background_image_url: String? = null,
    val profile_image_url_https: String? = null,
    val followers_count: Int? = null,
    val default_profile: Boolean? = null,
    val profile_image_url: String? = null,
    val profile_use_background_image: Boolean? = null,
    val screen_name: String? = null,
    val id: Long? = null,
    val name: String? = null,
    val following: Boolean? = null,
    val notifications: Boolean? = null,
    val created_at: String? = null
)


/*
{
  "entities": {
    "description": {
      "urls": []
    }
  },
  "verified": false,
  "listed_count": 0,
  "statuses_count": 1,
  "profile_text_color": "333333",
  "description": null,
  "friends_count": 0,
  "suspended": false,
  "profile_sidebar_border_color": "C0DEED",
  "id_str": "1292822141230931976",
  "profile_background_image_url": null,
  "geo_enabled": false,
  "status": {
    "entities": {
      "symbols": [],
      "user_mentions": [],
      "urls": [],
      "media": [
        {
          "id_str": "1292832498011893762",
          "media_url_https": "https://pbs.twimg.com/media/EfEQTqYUcAIy39P.jpg",
          "expanded_url": "https://twitter.com/twynony/status/1292832525769793537/photo/1",
          "id": "1292832498011893762",
          "url": "https://t.co/IyzPfe5whA",
          "type": "photo",
          "sizes": {
            "medium": {
              "resize": "fit",
              "h": 913,
              "w": 1200
            },
            "large": {
              "resize": "fit",
              "h": 1242,
              "w": 1632
            },
            "small": {
              "resize": "fit",
              "h": 518,
              "w": 680
            },
            "thumb": {
              "resize": "crop",
              "h": 150,
              "w": 150
            }
          },
          "display_url": "pic.twitter.com / IyzPfe5whA",
          "indices": [
            10,
            33
          ],
          "media_url": "http://pbs.twimg.com/media/EfEQTqYUcAIy39P.jpg"
        }
      ],
      "hashtags": []
    },
    "extended_entities": {
      "media": [
        {
          "id_str": "1292832498011893762",
          "media_url_https": "https://pbs.twimg.com/media/EfEQTqYUcAIy39P.jpg",
          "expanded_url": "https://twitter.com/twynony/status/1292832525769793537/photo/1",
          "id": "1292832498011893762",
          "url": "https://t.co/IyzPfe5whA",
          "type": "photo",
          "sizes": {
            "medium": {
              "resize": "fit",
              "h": 913,
              "w": 1200
            },
            "large": {
              "resize": "fit",
              "h": 1242,
              "w": 1632
            },
            "small": {
              "resize": "fit",
              "h": 518,
              "w": 680
            },
            "thumb": {
              "resize": "crop",
              "h": 150,
              "w": 150
            }
          },
          "display_url": "pic.twitter.com / IyzPfe5whA",
          "indices": [
            10,
            33
          ],
          "media_url": "http://pbs.twimg.com/media/EfEQTqYUcAIy39P.jpg"
        }
      ]
    },
    "favorited": false,
    "favorite_count": 0,
    "in_reply_to_user_id_str": null,
    "id_str": "1292832525769793537",
    "is_quote_status": false,
    "source": "<a href : https://mobile.twitter.com",
    "rel": "nofollow > Twitter Web App < /a>",
    "retweeted": false,
    "in_reply_to_screen_name": null,
    "possibly_sensitive": false,
    "id": "1292832525769793537",
    "geo": null,
    "lang": "in",
    "text": "bismillah https:/ / t.co / IyzPfe5whA ",
    "place": null,
    "created_at": "Mon Aug 10 14 : 37 : 48 + 0000 2020",
    "contributors": null,
    "in_reply_to_status_id_str": null,
    "truncated": false,
    "retweet_count": 0,
    "coordinates": null,
    "in_reply_to_status_id": null,
    "in_reply_to_user_id": null
  },
  "profile_image_url_https": "https: //abs.twimg.com/sticky/default_profile_images/default_profile_normal.png",
  "protected": false,
  "translator_type": "none",
  "followers_count": 0,
  "default_profile": true,
  "profile_image_url": "http://abs.twimg.com/sticky/default_profile_images/default_profile_normal.png",
  "profile_use_background_image": true,
  "screen_name": "twynony",
  "id": "1292822141230931976",
  "url": null,
  "lang": null,
  "name": "twynony",
  "time_zone": null,
  "favourites_count": 0,
  "has_extended_profile": true,
  "profile_sidebar_fill_color": "DDEEF6",
  "default_profile_image": true,
  "is_translator": false,
  "follow_request_sent": false,
  "profile_background_image_url_https": null,
  "contributors_enabled": false,
  "following": false,
  "profile_background_tile": false,
  "notifications": false,
  "created_at": "Mon Aug 10 13 : 57 : 01 + 0000 2020",
  "profile_link_color": "1DA1F2",
  "profile_background_color": "F5F8FA",
  "needs_phone_verification": false,
  "is_translation_enabled": false,
  "utc_offset": null,
  "location": null
}
* */