#!/bin/bash
ls
pip install pylint
FILE = 'test3.py'
pylint --output-format=parseable FILE || exit 0