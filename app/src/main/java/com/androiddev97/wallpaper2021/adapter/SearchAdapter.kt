import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.adapter.AdapterRandomPictures
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.list_item_search.view.*

class SearchAdapter(
    var context: Context,
    private var onCLickPicture: CLickListener,private val mListRandomPicturesModel: List<Photo>
) : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    private var itemPicturesRandomList: List<Photo> = mListRandomPicturesModel


    class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_search, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataListImage(list: List<Photo>) {
        this.itemPicturesRandomList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemPicturesRandomList.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val imageRandomList = itemPicturesRandomList[position]
        Glide.with(context).load(imageRandomList.src.tiny).override(400,400)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.itemView.img_search)
        holder.itemView.img_search.setOnClickListener {
            onCLickPicture.onClickRandom(imageRandomList)
        }
    }
}