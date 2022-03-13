When you implement viewpager in fragment and you want to go viewpager fragment to parent fragment then use following statement

interface OnNavigation{
  fun onNavigateTo(objects: Any?, data: String?)
}

class MainFrag: Fragment(){
   private lateinit var navigation: OnNavigation
  
   onCreateView(...){
       navigation = parentFragment?.parentFragmentManager?.fragments?.get(0) as OnNavigation //[AdminEmpFrag, AdminWorkerFrag]
   }
}


