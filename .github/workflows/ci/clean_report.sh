#!/bin/bash

# 디렉토리 설정
dir="./abc"

# 삭제할 디렉토리의 최대 숫자 설정
max_num=$(expr $GITHUB_RUN_NUMBER - 2)
echo "max_num: $max_num"

# 디렉토리 내의 모든 항목에 대해 반복
for file in "$dir"/*; do
  # 항목이 디렉토리이고, 이름이 숫자인 경우
  if [[ -d "$file" ]] && [[ $(basename "$file") =~ ^[0-9]+$ ]]; then
      # 디렉토리 이름(숫자)가 설정한 최대 숫자보다 작은 경우
      if [[ $(basename "$file") -lt $max_num ]]; then
          # 디렉토리 삭제
          rm -rfv "$file"
      fi
  fi
done
