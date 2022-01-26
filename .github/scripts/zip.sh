cd "${1}" || exit
ls
mkdir "./zipped"
for file in ./*;
do
  if [[ "$file" =~ "zipped" ]]
  then
      echo "output dir skipped"
  else
      zip -r "./zipped/$file.zip" "$file"
  fi
done