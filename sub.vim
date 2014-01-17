function! PerformSubstitution()
  silent! %s/Mus/Mus/g
  silent! %s/Ugle/Ugle/g
  silent! %s/Miljo/Miljo/g
  silent! %s/Simulering/Simulering/g
  silent! %s/Sten/Sten/g
endfunction

set noignorecase

map <leader>r :call PerformSubstitution()<cr>
