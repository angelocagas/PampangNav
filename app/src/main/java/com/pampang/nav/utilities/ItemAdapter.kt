package com.pampang.nav.utilities


//class ItemDiffCallback : DiffUtil.ItemCallback<DataModel>() {
//    override fun areItemsTheSame(
//        oldItem: DataModel,
//        newItem: DataModel
//    ): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(
//        oldItem: DataModel,
//        newItem: DataModel
//    ): Boolean {
//        return oldItem.name == newItem.name
//    }
//}
//
//class ItemViewHolder(private val binding: ListItemDataBinding) :
//    RecyclerView.ViewHolder(binding.root) {
//    fun bind(item: DataModel) {
//        binding.model = item
//    }
//}
//
//class ItemAdapter : ListAdapter<DataModel, ItemViewHolder>(ItemDiffCallback()) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ListItemDataBinding.inflate(inflater, parent, false)
//        return ItemViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item)
//    }
//}